# COM1028 - Software Engineering

# Assignment 1 - Report

**Student Name: Isabelle Wickens**

**Student ID: 6851539**

## 1.1 Proto Personas

### Persona 1

**James Williams, University Student**

James, age 19, is a first-year Psychology student at the University of Brighton.  Born in Kent, his mother a doctor and his father a secondary school Physics teacher, he lives less than an hour from the University but chose to stay in student accommodation with his friends to get a full university experience. James also has a younger sister who is in college training to be a musical theatre performer, but they love spending time together watching their favourite movie musicals when he is home. He is currently unemployed as he is focusing on his studies but used to be an English tutor for GCSE students while studying for his A Levels.

Passionate about film and an active member of the Film Society, James frequently hosts movie nights for his friends as he is known for his great recommendations. With great movie knowledge, he can identify the cast of most films. James grew up watching classics with his dad and now enjoys watching new releases with his peers. He enjoys a wide range of films and likes to follow specific actors and directors, his favourite being Steven Spielberg. He regularly researches film casts and behind-the-scenes trivia. He often uses his phone to browse ratings, read reviews and explore cast lists to learn more about his favourite films. He also likes to find lesser-known films and directors that he may not have heard of. James' ideal app would allow him to efficiently search for movies based on their cast, track the work of his favourite actors and directors, and find something to suit everyone's tastes. He likes the idea of having ratings and film details in one place and discovering up-and-coming filmmakers.

His ideal application would allow him to search efficiently by actor or director and access detailed filmographies, explore up-to-date ratings and reviews and create his own, and make watchlists to keep track of the films he would like to see. He hopes *FlickFinder* will save him time and help him explore and share a wider range of films that he can enjoy with friends and family. Also, he would like to learn more about how his parents grew up by enjoying hits from their childhood. For James, *FlickFinder* would help him share his passion for film with his friends and family.

### Persona 2

**Daisy King, Small Business Owner**

Daisy is a 42-year-old business owner from Manchester who runs a successful online craft store while raising two young children (aged 5 and 9) with her husband, a professional chef. She has a degree in Business Management from the University of Sheffield, and started her own company at 25, hand-making a range of products from jewellery to personalised cards. Due to her husband's busy work schedule, Daisy manages most parenting and household responsibilities whilst running her business from home. As she handles all aspects of her company, from product design to marketing and customer service, it's hard to spend time with her family while keeping her items at a high quality and satisfying her customers. She is always looking for ways to save time to prioritise time with her family.

In the little free time she has, she enjoys watching romantic comedies with her husband, and going to a local book club with friends. Many of her favourite books are currently being adapted into films and she is looking forward to watching them with her friends. She often struggles to find time to relax, so reading provides her a moment for herself. When working from home, she often puts on a film to keep them entertained but finds it difficult to find content that is appropriate and engaging without browsing endlessly or researching reviews. Daisy would like an app that helps her quickly find suitable films for her children, as well as something fun to watch with her husband. She would also like to stay informed about new releases and keep track of upcoming adaptations of her favourite books. She is used to using apps to run her business, so *FlickFinder* would fit into her routine as another organisation tool. For Daisy, *FlickFinder* is a practical solution to help reduce daily stress and make more time for her to spend with her family.

## 1.2 Scenario

**James' Movie Marathon**

James is organising a movie marathon for his best friend, Olivia, to celebrate her 20th birthday. He has decorated his student accommodation and bought snacks, but needs to select the line-up for the evening. He is having 11 friends over, several from the university film society, and must find films to suit their range of tastes and make sure that every choice is perfect.

He has worked out there will be time for five films, so James opens the *FlickFinder* application on his phone to start looking. He creates a watchlist titled 'Olivia's Movie Marathon' to keep track of his selection, then navigates to the search bar. Olivia's favourite actor is Andrew Garfield, so he searches this name, and applies filters to sort results by  highest-rated film. He initially selects the first five results, and adds them to his watchlist for reference, but notices that two are from *The Amazing Spider-Man* series. To maintain a wide range of films for the night and avoid redundancy, he removes one from the list. James then searches again, but filters by director, and selects a title with high ratings from a director he had not heard of and adds it to his watchlist.

Happy with his choices, he also reviews the cast of each film in advance to answer any potential questions from his friends and makes some information cards for his friends to read and keep as mementos, including the film name, director, cast, release year, average rating and some fun facts.

The final selection contains multiple genres, including a musical, drama, and an action-comedy, each from a different director. By using *FlickFinder*, James is able to plan the entire movie marathon in under 30 minutes. This would usually have taken over an hour to cross-reference different websites (IMDB (IMDb, no date), Rotten Tomatoes (Rotten Tomatoes, no date), film forums...). The movie marathon is a huge success, and the guests are very pleased with the lineup. Olivia was happy to see her favourite actor in all of the films, and everyone is impressed with the care James put into the experience. James rated the movies they watched and encouraged his friends to do the same. *FlickFinder* not only helped him to pick the films but allowed him to create a memorable experience. Following the event, James is asked to organise another film night.


## 1.3 User Stories

### User Story 1

**Searching By Actor**

As an actor, I want to search for films by an actor's name so that I can track my own filmography and monitor audience response to my work.

### User Story 2

**Searching By Director**

As a film student, I want to search for films by director so that I can explore all of their work in one place.

### User Story 3

**Ordering Results By Rating**

As a producer, I want to order search results by viewer rating so that I see which films are trending and performing well so that I can make informed decisions when funding new projects.

### User Story 4

**Filtering Results**

As a busy parent, I want to filter films by age rating and content type so that I can quickly find something safe and suitable for my children to watch.

### User Story 5

**Searching By Year**

As a nostalgic film fan, I want to search for movies by release year so that I can easily find films from my childhood or a specific era.

### User Story 6

**Limiting Results**

As a developer, I want to limit the number of search results returned per query to ensure that the database performance remains fast and scalable.


## 2 Critical Analysis and Reflection

### 2.1 Reflection

Developing the requirements and backend for *FlickFinder* involved using key software engineering techniques. Creating personas and scenarios helped me understand the project from the user's perspective. I gave each user a distinct background and motivation, making it easier to write varied user stories. Considering roles such as administrators was more challenging, and pushed me to think beyond typical user interactions, reflecting a broader range of use cases.

For the backend, I used Java, following a layered architecture. I built a RESTful API using Javalin, managing dependencies with Maven. Although new technologies for me, coding the models, DAOs and controllers was straightforward as many operations followed similar patterns.

Using SQLite for querying the database, the main challenge involved using joins and subqueries, but referring to the ERD helped me understand the table relationships and structure the queries correctly. I used JUnit for unit tests and Hamcrest for integration testing. Hamcrest's matchers, such as *hasItems*, made the assertions more flexible (Testing with Hamcrest | Baeldung, no date). 

This project improved my technical and planning skills, increasing understanding of how techniques such as personas and scenarios help clarify user needs. In future projects, I will aim to include a wider range of use roles, expanding the range of perspectives portrayed. Overall, I feel confident applying these technologies and techniques again to create effective, well-tested, user-focused software.


### 2.2 Professional Aspects

To ensure the backend was maintainable and scalable, I used a layered architecture, applying MVC principles (Sommerville, 2015). This allows for high cohesion and modularity as each layer has a specific responsibility, making it easy to test components separately using unit tests. This architecture was particularly useful as the presentation layer was unknown. The system is maintainable as changes to one layer do not affect others. It is scalable due to its loose coupling, which makes it easy to add services such as authentication, demonstrating its longevity as it can be updated and extended. Adding limits to search queries reduces server load when handling large datasets, increasing energy efficiency as it requires less processing power.

The Singleton class used for the unit testing database means the entire application uses the same connection, allowing for efficient resource management (careermgr, 2024). The MovieRating model extends the Movie model, demonstrating inheritance. I used encapsulation with private attributes and separation of responsibilities across layers. In the DAOs, I used method overriding to make the limit parameter optional, an example of polymorphism. These object-oriented techniques made the code more maintainable by reducing redundancy.

As the app handles personal data, GDPR needs to be considered (VinciWorks, 2022). Data should be stored securely, and only used for specific purposes. Users must consent to data collection, having the right to access and delete their information. Authentication should be used, with data stored on an encrypted server. To improve sustainability, cloud-based storage could be considered to reduce the need for physical servers (Buchan, 2024). These ethical, environmental and privacy concerns should be considered if the project were developed further. To improve professional responsibility in the future, I would add more detailed comments to aid readability and support collaboration.


## 3. References

Buchan, J. (2024) ‘Green app development: how to create an app more sustainably’, Zudu, 19 January. Available at: https://zudu.co.uk/blog/green-app-development-how-to-create-an-app-more-sustainably/ (Accessed: 10 May 2025).

careermgr (2024) ‘Mastering the Singleton Pattern in Software Architecture: Efficiency and Global Access’, Curate Partners Merch, 14 October. Available at: https://curatepartners.com/blogs/skills-tools-platforms/mastering-the-singleton-pattern-in-software-architecture-efficiency-and-global-access/ (Accessed: 10 May 2025).

IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows (no date). Available at: https://www.imdb.com/ (Accessed: 10 May 2025).

Rotten Tomatoes: Movies | TV Shows | Movie Trailers | Reviews | Rotten Tomatoes (no date). Available at: https://www.rottentomatoes.com/ (Accessed: 10 May 2025).

Sommerville, I. (2015). Software engineering. 10th ed. Pearson.

Testing with Hamcrest | Baeldung (no date). Available at: https://www.baeldung.com/java-junit-hamcrest-guide (Accessed: 10 May 2025).

VinciWorks (2022) ‘What is GDPR in simple terms?’, VinciWorks, 31 March. Available at: https://vinciworks.com/blog/what-is-gdpr-in-simple-terms/ (Accessed: 10 May 2025)



