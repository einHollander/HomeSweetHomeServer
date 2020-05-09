package bedbrains.platform

import bedbrains.homesweethomeserver.DataRepository
import bedbrains.shared.datatypes.devices.Device
import bedbrains.shared.datatypes.rules.Rule
import bedbrains.shared.datatypes.rules.RuleValue
import bedbrains.shared.datatypes.update
import bedbrains.shared.datatypes.upsert

object DataProvider {
    val devices: List<Device>
        get() = DataRepository.devices

    val rules: List<Rule>
        get() = DataRepository.rules

    val values: List<RuleValue>
        get() = DataRepository.values

    fun updateDevice(device: Device) {
        DataRepository.devices.update(device) { it.uid == device.uid }
    }

    fun upsertRule(rule: Rule) {
        DataRepository.rules.upsert(rule) { it.uid == rule.uid }
    }

    fun upsertValue(value: RuleValue) {
        DataRepository.values.upsert(value) { it.uid == value.uid }
    }
}