# Site Object Specification

## Description

The Site object is an object designed to define the seismic station used as part
of the [Pick](Pick.md) format.  Site uses the
[JSON standard](http://www.json.org).

## Usage

Site is intended for use as part of the [Pick](Pick.md) format, which is used
as part of the [Location](Location.md) Format in input/output messages for
seismic processing algorithms.

## Output

```json
    {
      "Station"   : String,
      "Channel"   : String,
      "Network"   : String,
      "Location"  : String,
      "Latitude"  : Number,
      "Longitude" : Number,
      "Elevation" : Number
    }
```

## Glossary

**Required Values:**

These are the values **required** to define a Site.

* Station - A string containing the station code.
* Network - A string containing network code.
* Latitude - A decimal number that identifies the latitude of this site in degrees.
* Longitude - A decimal number that identifies the longitude of this site in degrees.
* Elevation - A decimal number that identifies the elevation of this site in meters.

**Optional Values:**

The following are supplementary values that **may or may not** be provided as
part of a Site.

* Channel - A string containing the channel code.
* Location - A string containing the location code.
