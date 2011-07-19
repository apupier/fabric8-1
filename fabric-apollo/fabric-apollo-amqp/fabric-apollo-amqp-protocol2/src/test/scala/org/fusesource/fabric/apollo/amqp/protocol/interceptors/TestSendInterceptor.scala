package org.fusesource.fabric.apollo.amqp.protocol.interceptors

import org.fusesource.fabric.apollo.amqp.protocol.interfaces.Interceptor
import org.fusesource.fabric.apollo.amqp.codec.interfaces.AMQPFrame
import collection.mutable.Queue

/**
 *
 */

class TestSendInterceptor (test:(AMQPFrame, Queue[() => Unit]) => Unit) extends Interceptor {

  def send(frame: AMQPFrame, tasks: Queue[() => Unit]) = {
    test(frame, tasks)
    outgoing.send(frame, tasks)
  }

  def receive(frame: AMQPFrame, tasks: Queue[() => Unit]) = incoming.receive(frame, tasks)
}