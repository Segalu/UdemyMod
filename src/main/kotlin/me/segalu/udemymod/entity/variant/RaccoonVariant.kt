package me.segalu.udemymod.entity.variant

enum class RaccoonVariant(val id: Int) {
    DEFAULT(0),
    DARK(1),
    RED(2);

    companion object {
        private val BY_ID = arrayOf(*values())

        fun byId(id: Int): RaccoonVariant {
            return BY_ID[id % BY_ID.size]
        }
    }

}