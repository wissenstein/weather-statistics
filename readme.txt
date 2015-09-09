A simple site to display air temperature in Kharkiv at midnight, 
in the morning, at midday and in the evening on a day chosen by a user. 

MongoDB is required.

If no data for a certain day is kept in the database, the following service 
is asked: https://sinoptik.ua/погода-харьков/{date}, where {date} is the date 
in format yyyy-MM-dd, and its response is parsed to extract temperature values.
