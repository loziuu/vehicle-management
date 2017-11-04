package pl.loziuu.ivms.maintenance.journal.domain

import org.springframework.stereotype.Component
import pl.loziuu.ivms.maintenance.journal.port.JournalCommand

@Component
class DefaultJournalCommand(val repository: JournalRepository) : JournalCommand {

    override fun setupJournal(vehicleId: Long) : Long {
        return repository.save(Journal(vehicleId = vehicleId)).id
    }
}