package bedbrains.homesweethomeserver.rest

import bedbrains.shared.datatypes.celsius
import bedbrains.shared.datatypes.devices.Device
import bedbrains.shared.datatypes.devices.Heating
import bedbrains.shared.datatypes.devices.Light
import bedbrains.shared.datatypes.rules.*
import bedbrains.shared.datatypes.upsert
import org.springframework.web.bind.annotation.*


@RestController
class Controller {

    val devices = mutableListOf<Device>(
            Light("9998", "Keller", "Decke", true),
            Heating("9999", "Keller", "Heizung", 22.celsius, 20.celsius)
    )
    val rules = mutableListOf<Rule>(
            WeeklyRule("10000", "Normal").apply { timeSpans.add(WeeklyTimeSpan(WeeklyTime.now, WeeklyTime.MAX)) },
            WeeklyRule("10001", "Ferien")
    )

    @GetMapping("/devices")
    fun devices(): List<Device.JSON> = devices.map { it.toJSON() }

    @GetMapping("/device")
    fun device(@RequestParam(name = "uid") uid: String): Device.JSON? = devices.find { it.uid == uid }?.toJSON()

    @PostMapping("device")
    fun device(@RequestBody json: Device.JSON) {
        val device = json.toDevice()!!
        devices.upsert(device) { it.uid == device.uid }
    }

    @GetMapping("/rules")
    fun rules(): List<Rule.JSON> = rules.map { it.toJSON() }

    @GetMapping("/rule")
    fun rule(@RequestParam(name = "uid") uid: String): Rule.JSON? = rules.find { it.uid == uid }?.toJSON()

    @PostMapping("rule")
    fun device(@RequestBody json: Rule.JSON) {
        val rule = json.toRule()
        rules.upsert(rule) { it.uid == rule.uid }
    }

}
