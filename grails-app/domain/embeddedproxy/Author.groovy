package embeddedproxy

class Author {
    String name
    Publisher publisher
    Period activePeriod

    static embedded = ['activePeriod']

    static constraints = {
        publisher nullable: true
    }
}
