package bedbrains.homesweethomeserver

import bedbrains.platform.UIDProvider
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@SpringBootApplication
class HomeSweetHomeServerApplication

fun main(args: Array<String>) {
    runApplication<HomeSweetHomeServerApplication>(*args)

    val clientIdentifier: String = UIDProvider.newUID

    val client: Mqtt5AsyncClient = Mqtt5Client.builder()
            .identifier(clientIdentifier)
            .serverHost("localhost")
            .serverPort(1883)
            .buildAsync()

    client.connect()

    client.subscribeWith()
            .topicFilter("test")
            .qos(MqttQos.EXACTLY_ONCE)
            .callback {
                println("Received " + String(it.payloadAsBytes) + " from " + it.topic)
            }
            .send()

    // save all deivice settings


    val time = LocalDateTime.now()
    val folder = File("./devices/" + time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "/")
    folder.mkdirs()

    for (device in DataRepository.devices) {
        val fileName: String = ("/" + device.name + "_" + device.room + "_" + device.uid)


        FileOutputStream(folder.toPath().toString() + fileName).also { fos ->
            ObjectOutputStream(fos).also {
                it.writeObject(device)
                it.close()
            }
            fos.close()

            println("Name" + folder.toPath())
        }
    }
}