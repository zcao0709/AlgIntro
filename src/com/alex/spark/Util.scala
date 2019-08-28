package com.alex.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner}

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

/**
 * Created by caozhennan on 2019/8/28 7:20 下午.
 */
object Util {
  class PrimaryKeyPartitioner[K, S](partitions: Int) extends Partitioner {
    val delegate = new HashPartitioner(partitions)

    override def numPartitions: Int = delegate.numPartitions

    override def getPartition(key: Any): Int = {
      val k = key.asInstanceOf[(K, S)]
      delegate.getPartition(k._1)
    }
  }

  def groupByKeyAndSortBySecondaryKey[K: Ordering : ClassTag, S : Ordering : ClassTag, V : ClassTag](pairRDD : RDD[((K, S), V)], partitions: Int): RDD[(K, List[(S, V)])] = {
    val partitioner = new PrimaryKeyPartitioner[K, S](partitions)

    implicit def ordering: Ordering[(K, S)] = Ordering.Tuple2

    pairRDD.repartitionAndSortWithinPartitions(partitioner).mapPartitions(iter => groupSorted[K, S, V](iter))
  }

  def groupSorted[K, S, V](iter: Iterator[((K, S), V)]): Iterator[(K, List[(S, V)])] = {
    val res = List[(K, ArrayBuffer[(S, V)])]()
    iter.foldLeft(res)((list, next) => list match {
      case Nil =>
        val ((firstKey, secondKey), value) = next
        List((firstKey, ArrayBuffer((secondKey, value))))

      case head :: _ =>
        val (curKey, valueBuf) = head
        val ((firstKey, secondKey), value) = next
        if (!firstKey.equals(curKey)) {
          (firstKey, ArrayBuffer((secondKey, value))) :: list
        } else {
          if (valueBuf.size > 1000) {
            valueBuf.remove(0)
          }
          valueBuf.append((secondKey, value))
          list
        }
    }).map { case (key, buf) => (key, buf.toList) }.iterator
  }
}
