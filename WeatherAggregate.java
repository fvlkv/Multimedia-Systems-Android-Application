package com.example.projektsm;


public class WeatherAggregate
{
    private Current current;

    private String timezone;

    private String timezone_offset;

    private String lon;

    private Hourly[] hourly;

    private String lat;

    public Current getCurrent ()
    {
        return current;
    }

    public void setCurrent (Current current)
    {
        this.current = current;
    }

    public String getTimezone ()
    {
        return timezone;
    }

    public void setTimezone (String timezone)
    {
        this.timezone = timezone;
    }

    public String getTimezone_offset ()
    {
        return timezone_offset;
    }

    public void setTimezone_offset (String timezone_offset)
    {
        this.timezone_offset = timezone_offset;
    }

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public Hourly[] getHourly ()
    {
        return hourly;
    }

    public void setHourly (Hourly[] hourly)
    {
        this.hourly = hourly;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "długość geograficzna = "+lon+"\nszerokość geograficzna = "+lat+"\ntemperatura = "+getCurrent().getTemp()+"°C\nciśnienie = "+getCurrent().getPressure()+"hPa\nwilgotność = "+getCurrent().getHumidity()+"%\nopis pogody = "+getCurrent().getWeather()[0].getDescription();
    }
}