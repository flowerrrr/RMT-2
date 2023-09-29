package de.flower.rmt2.db.entity.event

enum class EventType(
    private val clazz: Class<out de.flower.rmt2.db.entity.event.Event>
) {

    Event(de.flower.rmt2.db.entity.event.Event::class.java),
    Match(de.flower.rmt2.db.entity.event.Match::class.java),
    Training(de.flower.rmt2.db.entity.event.Training::class.java),
    Tournament(de.flower.rmt2.db.entity.event.Tournament::class.java);

    companion object {
        fun from(event: de.flower.rmt2.db.entity.event.Event): EventType {
            return values().find { it.clazz == event.javaClass }
                ?: throw IllegalArgumentException("Unknown eventType [${event.javaClass.simpleName}]")
        }

        fun isMatch(event: de.flower.rmt2.db.entity.event.Event) = event.javaClass == Match.clazz

        fun isSoccerEvent(event: de.flower.rmt2.db.entity.event.Event) = event is AbstractSoccerEvent
    }

}