package br.com.alurafood.pagamentos.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoAMQPConfiguration {

    /**
     * Criando uma fila com o nome "pagamento.concluido" e que não será durável
     */
    @Bean
    public Queue criaFila() {
//        return new Queue("pagamento.concluido", false);
        return QueueBuilder.nonDurable("pagamento.concluido").build();
    }

    /**
     * Criando um administrador do RabbitMQ
     */
    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    /**
     * Inicializar o RabbitMQ quando subir a aplicação
     */
    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}
