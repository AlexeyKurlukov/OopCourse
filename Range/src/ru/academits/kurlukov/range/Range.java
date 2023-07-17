package ru.academits.kurlukov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        double minTo = Math.min(this.to, range.to);
        double maxFrom = Math.max(this.from, range.from);

        if (minTo <= maxFrom) {
            return null;
        }

        return new Range(maxFrom, minTo);
    }

    public Range[] getUnion(Range range) {
        double minFrom = Math.min(this.from, range.from);
        double maxTo = Math.max(this.to, range.to);

        if (minFrom == this.from && maxTo == this.to) {
            return new Range[]{this};
        }

        if (minFrom == range.from && maxTo == range.to) {
            return new Range[]{range};
        }

        return new Range[]{new Range(minFrom, maxTo)};
    }

    public Range[] getDifference(Range range) {
        if (this.from >= range.to || this.to <= range.from) {
            return new Range[]{this};
        }

        if (this.from >= range.from && this.to <= range.to) {
            return new Range[]{};
        }

        if (this.from < range.from && this.to > range.to) {
            return new Range[]{
                    new Range(this.from, range.from),
                    new Range(range.to, this.to)
            };
        }

        if (this.from < range.from) {
            return new Range[]{new Range(this.from, range.from)};
        }

        return new Range[]{new Range(range.to, this.to)};
    }
}