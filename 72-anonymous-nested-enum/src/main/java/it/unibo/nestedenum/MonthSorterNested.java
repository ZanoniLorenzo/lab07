package it.unibo.nestedenum;

import java.util.Comparator;
//import java.util.Locale;
//import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month{
        January(31),
        February(28),
        March(31),
        April(30),
        May(31),
        June(30),
        July(31),
        August(31),
        September(30),
        October(31),
        November(30),
        December(31);

        private final int days;

        private Month(int days){
            this.days = days;
        }

        public int getDays(){
            return this.days;
        }

        Month fromString(String month){
            if (month == null) throw new IllegalArgumentException("No month with name: null");
            String m = month.toLowerCase();
            Month found = null;
            for (Month mon : Month.values()) {
                String name = mon.name().toLowerCase();
                if (name.startsWith(m)) {
                    if (found != null) {
                        throw new IllegalArgumentException("Ambiguous month: " + month);
                    }
                    found = mon;
                }
            }
            if (found == null) throw new IllegalArgumentException("No month with name: " + month);
            return found;
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            public int compare(String s1, String s2) {
                Month month1 = Month.January.fromString(s1);
                Month month2 = Month.January.fromString(s2);
                if(month1.getDays() > month2.getDays()){
                    return 1;
                } else if (month1.getDays() < month2.getDays()){
                    return -1;
                }
                return 0;
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        Comparator<String> comparator = new Comparator<String>(){
            public int compare(String s1, String s2) {
                Month month1 = Month.January.fromString(s1);
                Month month2 = Month.January.fromString(s2);
                if (month1.ordinal() > month2.ordinal()){
                    return 1;
                } else if (month1.ordinal() < month2.ordinal()){
                    return -1;
                }
                return 0;
            }
        };
        return comparator;
    }
}
