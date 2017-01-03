# Database_Application
Scoolproject about a data application.
Mimics a admin control window for a bookdealer.

Uses MySQL.

Saves data in a database called 'bookstore', it contains four tables, 'store', 'book', 'author' and 'have'.

store:

| Field  | Type        | Null | Key | Default | Extra          |

| id     | int(11)     | NO   | PRI | NULL    | auto_increment |

| name   | varchar(32) | NO   |     | NULL    |                |

| adress | varchar(32) | NO   |     | NULL    |                |

book:


| Field    | Type        | Null | Key | Default | Extra          |

| id       | int(11)     | NO   | PRI | NULL    | auto_increment |

| name     | varchar(32) | NO   |     | NULL    |                |

| authorid | int(11)     | NO   |     | NULL    |                |

author:

| Field | Type        | Null | Key | Default | Extra          |

| id    | int(11)     | NO   | PRI | NULL    | auto_increment |

| name  | varchar(32) | YES  |     | NULL    |                |

| age   | int(11)     | YES  |     | NULL    |                |

have:

| Field | Type        | Null | Key | Default | Extra          |

| id    | int(11)     | NO   | PRI | NULL    | auto_increment |

| name  | varchar(32) | YES  |     | NULL    |                |

| age   | int(11)     | YES  |     | NULL    |                |
