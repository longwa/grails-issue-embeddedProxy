package embeddedproxy

import grails.gorm.dirty.checking.DirtyCheck
import grails.validation.Validateable

/**
 * Represents a period of time with a begin and end date (optional)
 */
@DirtyCheck
class Period implements Validateable {
    Date startDate
    Date endDate

    static constraints = {
        endDate(nullable: true)
    }
}
