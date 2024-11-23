| Endpoint      | HTTP Method | Request Body | Status      | Response body | Description                                                                  |
|---------------|-------------|--------------|-------------|---------------|------------------------------------------------------------------------------|
| /books        | GET         |              | 200         | Book[]        | Get all the books in the catalog.                                            |
| /books        | POST        | Book         | 201<br/>422 | Book<br/>     | Add a new book to the catalog.<br/>A book with the same ISBN already exists. |
| /books/{isbn} | GET         |              | 200<br/>404 | Book<br/>     | Get the book with the given ISBN.<br/>No book with given ISBN exists.        |
| /books/{isbn} | PUT         | Book         | 200<br/>201 | Book<br/>Book | Update the book with the given ISBN.<br/>Create a book with the given ISBN.  |
| /books/{isbn} | DELETE      |              | 204         |               | Delete the book with the given ISBN                                          |