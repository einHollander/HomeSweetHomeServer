package bedbrains.homesweethomeserver.rest

import bedbrains.shared.datatypes.devices.Device
import bedbrains.shared.datatypes.devices.Heating
import bedbrains.shared.datatypes.devices.Light
import bedbrains.shared.datatypes.rules.*
import bedbrains.shared.datatypes.temperature.celsius
import bedbrains.shared.datatypes.upsert
import org.springframework.web.bind.annotation.*

@RestController
class Controller {

    val devices = mutableListOf<Device>(
            Heating("9999", "Keller", "Heizung", 22.celsius, 20.celsius),
            Light("9998", "Keller", "Decke", true)
    )
    val rules = mutableListOf<Rule>(
            WeeklyRule("10000", "Normal").apply { timeSpans.add(WeeklyTimeSpan(WeeklyTime.now, WeeklyTime.MAX)) },
            WeeklyRule("10001", "Ferien")
    )
    val values = mutableListOf<RuleValue>(
            RuleValue("10002", "Default", 22.celsius, true),
            RuleValue("10003", "Winter", 23.celsius, true)
    )

    @GetMapping("/v1/devices")
    fun devices() = devices

    @GetMapping("/v1/device")
    fun device(@RequestParam(name = "uid") uid: String) = devices.find { it.uid == uid }

    @PostMapping("/v1/device")
    fun postDevice(@RequestBody device: Device) {
        devices.upsert(device) { it.uid == device.uid }
    }

    @DeleteMapping("/v1/device")
    fun deleteDevice(@RequestParam(name = "uid") uid: String) {
        devices.removeIf { it.uid == uid }
    }

    @GetMapping("/v1/rules")
    fun rules() = rules

    @GetMapping("/v1/rule")
    fun rule(@RequestParam(name = "uid") uid: String) = rules.find { it.uid == uid }

    @PostMapping("/v1/rule")
    fun postrule(@RequestBody rule: Rule) {
        rules.upsert(rule) { it.uid == rule.uid }
    }

    @DeleteMapping("/v1/rule")
    fun deleteRule(@RequestParam(name = "uid") uid: String) {
        rules.removeIf { it.uid == uid }
    }

    @GetMapping("/v1/values")
    fun values() = values

    @GetMapping("/v1/value")
    fun value(@RequestParam(name = "uid") uid: String) = values.find { it.uid == uid }

    @PostMapping("/v1/value")
    fun postValue(@RequestBody value: RuleValue) {
        values.upsert(value) { it.uid == value.uid }
    }

    @DeleteMapping("/v1/value")
    fun deleteValue(@RequestParam(name = "uid") uid: String) {
        values.removeIf { it.uid == uid }
    }

}
