package com.tgzhao.clannad.akka

import akka.actor.{Props, ActorSystem, Actor}
import akka.actor.Actor.Receive

/**
 * Created by tgzhao on 2016/10/18.
 */
class HelloActor extends Actor {

  override def receive: Receive = {
    case "hello" => println("您好！")
    case _       => println("您是?")
  }
}

object HelloActor {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HelloSystem")
    // 缺省的Actor构造函数
    val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
    helloActor ! "hello"
    helloActor ! "喂"
  }
}
