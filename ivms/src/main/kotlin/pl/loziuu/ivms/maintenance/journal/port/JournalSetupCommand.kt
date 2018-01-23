package pl.loziuu.ivms.maintenance.journal.port

interface JournalSetupCommand {
    fun setupJournal(vehicleId: Long): Long
    fun removeJournal(vehicleId: Long)
}