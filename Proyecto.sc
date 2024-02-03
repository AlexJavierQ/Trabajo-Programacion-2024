import akka.actor.ActorSystem
import akka.stream.scaladsl.{Source, Sink}

object EjemploReactiveScala extends App {

  // Creamos un sistema de actores y un materializador para trabajar con flujos
  implicit val system: ActorSystem = ActorSystem("EjemploReactiveScala")
  implicit val ec = system.dispatcher

  // Creamos un flujo de datos (Source) con números del 1 al 10
  val numerosFuente = Source(1 to 10)

  // Aplicamos algunos operadores en el flujo
  val resultado = numerosFuente
    .map(_ * 2)           // Multiplicamos cada número por 2
    .filter(_ % 3 == 0)   // Filtramos solo los múltiplos de 3
    .runWith(Sink.seq)     // Recolectamos los resultados en una secuencia

  // Imprimimos el resultado
  resultado.foreach { listaResultados =>
    println("Resultado final: " + listaResultados)
    system.terminate()  // Cerramos el sistema de actores después de imprimir el resultado
  }
}
