package pl.loziuu.ivms.maintenance.journal.port

interface JournalCommand {

    fun setupJournal(vehicleId: Long) : Long
}