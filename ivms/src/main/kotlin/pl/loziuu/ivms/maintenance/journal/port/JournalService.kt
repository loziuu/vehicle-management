package pl.loziuu.ivms.maintenance.journal.port

interface JournalService {
    fun setupJournal(vehicleId: Long): Long
    fun removeJournal(vehicleId: Long)
}