package com.danicode.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* https://profile.es/blog/que-es-spring-batch-ejemplo/#%C2%BFQue_es_Spring_Batch
  Un proceso batch o proceso por lote es un proceso pensado para trabajar con
  volúmenes muy grandes de datos y generalmente de una forma programada.
  Es decir, sin intervención humana.
 */

/* Por ejemplo, la carga de un fichero enorme con millones de registros;
   o bien un proceso nocturno que, a partir de una serie de consultas,
   envía una gran cantidad de e-mails, sms, etc. Esto sería un proceso batch.
*/

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}