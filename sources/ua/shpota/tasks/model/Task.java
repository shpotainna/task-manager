package ua.shpota.tasks.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    private String title;
    private Date time;
    private Date start;
    private Date end;
    private int interval;
    private boolean active;
    private boolean isRepeated;

    /**
     * Constructs non repeated inactive task
     *
     * @param title
     * @param time
     */
    public Task(String title, Date time) {
        if (title == null || time == null) {
            throw new IllegalArgumentException("The title and time of the task can not be null.");
        }

        this.title = title;
        this.time = time;
        active = false;
        isRepeated = false;
    }

    /**
     * Constructs repeated inactive task
     *
     * @param title
     * @param start
     * @param end
     * @param interval
     */
    public Task(String title, Date start, Date end, int interval) {
        if (title == null || start == null || end == null) {
            throw new IllegalArgumentException("Title, Start and end can not be null.");
        }
        if (interval <= 0) {
            throw new IllegalArgumentException("The interval must be positive.");
        }
        if (start.after(end)) {
            throw new IllegalArgumentException("Start must be before end.");
        }

        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        active = false;
        isRepeated = true;
    }

    /**
     * Sets task name
     *
     * @param title
     */
    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("The title of the task can not be null.");
        }

        this.title = title;
    }

    /**
     * Gets task name
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets task the state of activity
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets task the state of activity
     *
     * @return active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets non repeated task the time
     *
     * @param time
     */
    public void setTime(Date time) {
        if (time == null) {
            throw new IllegalArgumentException("The time of the task can not be null.");
        }

        isRepeated = false;
        start = new Date(time.getTime());
        end = new Date(time.getTime());
        interval = 0;
        this.time = new Date(time.getTime());
    }

    /**
     * Gets non repeated task the time
     *
     * @return time
     */
    public Date getTime() {
        Date time;
        if (isRepeated) {
            time = new Date(start.getTime());
        } else {
            time = new Date(this.time.getTime());
        }

        return time;
    }

    /**
     * Sets repeated task the time and interval
     *
     * @param start
     * @param end
     * @param interval
     */
    public void setTime(Date start, Date end, int interval) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end can not be null.");
        }
        if (interval <= 0) {
            throw new IllegalArgumentException("The interval must be positive.");
        }
        if (start.after(end)) {
            throw new IllegalArgumentException("Start must be before end.");
        }

        isRepeated = true;
        time = new Date(start.getTime());
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        this.interval = interval;
    }

    /**
     * Gets repeated task the start time
     *
     * @return start
     */
    public Date getStartTime() {
        Date start;
        if (isRepeated) {
            start = new Date(this.start.getTime());
        } else {
            start = new Date(time.getTime());
        }
        return start;
    }

    /**
     * Gets repeated task the end time
     *
     * @return end
     */
    public Date getEndTime() {
        Date end;
        if (isRepeated) {
            end = new Date(this.end.getTime());
        } else {
            end = new Date(time.getTime());
        }
        return end;
    }

    /**
     * Gets repeated task the interval
     *
     * @return interval
     */
    public int getRepeatInterval() {
        int interval;
        if (isRepeated) {
            interval = this.interval;
        } else {
            interval = 0;
        }
        return interval;
    }

    /**
     * Check task is repeated
     *
     * @return isRepeated
     */
    public boolean isRepeated() {
        return isRepeated;
    }

    /**
     * Returns the next time the task after a specified time
     *
     * @param current
     * @return nextTimeAfter
     */
    public Date nextTimeAfter(Date current) {
        if (current == null) {
            throw new IllegalArgumentException("Current date can not be null.");
        }
        Date nextTimeAfter = null;
        if (active) {
            if (!isRepeated) {
                if (current.before(time)) {
                    nextTimeAfter = new Date(time.getTime());
                }
            } else {
                if (current.before(start)) {
                    nextTimeAfter = new Date(start.getTime());
                } else {
                    Date thisTime = this.start;
                    while (thisTime.before(current) || thisTime.equals(current)) {
                        thisTime = new Date(thisTime.getTime() + interval * 1000);
                    }

                    if (thisTime.before(end) || thisTime.equals(end)) {
                        nextTimeAfter = new Date(thisTime.getTime());
                    }
                }
            }
        }

        return nextTimeAfter;
    }

    @Override
    public String toString() {
        String s;
        if (isRepeated) {
            s = "Task title: " + title + ", start time: " + start + ", end time: " +
                    end + ", interval: " + interval + ". Task is repeated: " + isRepeated +
                    " and is active: " + active + ".";
        } else {
            s = "Task title: " + title + ", time: " + time + ". Task is repeated: " +
                    isRepeated + " and is active: " + active + ".";
        }

        return s;
    }

    @Override
    public int hashCode() {
        int result = 31 * title.hashCode();
        if (time != null) {
            result = time.hashCode() + result;
        }
        if (start != null) {
            result = start.hashCode() + result;
        }
        if (end != null) {
            result = end.hashCode() + result;
        }

        result = result * interval;

        return result;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject == null) {
            return false;
        }
        if (!getClass().equals(anObject.getClass())) {
            return false;
        }

        Task anotherTask = (Task) anObject;

        return title.equals(anotherTask.title) &&
                equalDates(time, anotherTask.time) &&
                equalDates(start, anotherTask.start) &&
                equalDates(end, anotherTask.end) &&
                interval == anotherTask.interval &&
                active == anotherTask.active &&
                isRepeated == anotherTask.isRepeated;
    }

    private boolean equalDates(Date first, Date second) {
        if (first == null && second == null) {
            return true;
        } else if (first == null || second == null) {
            return false;
        }

        return first.equals(second);
    }
}