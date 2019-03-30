package garits.singleton;

import database.domain.job.Booking;

public class BookingSingleton
{
    private static BookingSingleton instance = null;
    private static Booking booking;

    public static BookingSingleton getInstance()
    {
        if(instance == null)
            instance = new BookingSingleton();

        return instance;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        BookingSingleton.booking = booking;
    }
}
