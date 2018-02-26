package pl.loziuu.ivms.ddd.infrastructure

import java.sql.Date
import java.time.LocalDate
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalDatePersistenceConverter : AttributeConverter<LocalDate, Date> {
    override fun convertToEntityAttribute(dbData: Date?): LocalDate? {
        if (dbData != null) {
            return LocalDate.parse(getDate(dbData));
        }
        return null;
    }

    override fun convertToDatabaseColumn(attribute: LocalDate?): Date? {
        if (attribute != null) {
            return Date.valueOf(attribute)
        }
        return null;
    }

    private fun getDate(dbData: Date): String {
        return dbData.toString().substring(0, 10)
    }
}