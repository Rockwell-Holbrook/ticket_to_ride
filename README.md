# ticket_to_ride

****************************************************** PHASE 1 *******************************************************

Objectives
1. Implement all operations needed before initializing game. These operations must include the
ability for players to register, login, create a new game, and join an existing game that has not yet
started. You must also provide a means of starting a game with two to five players.
2. Understand how to create and document a design.
3. Understand and be able to use the com.example.shared.model-view-presenter, observer, and facade patterns.
New Patterns
1. Model-View-Presenter
2. Observer
3. Facade
Requirements
Design
Create and document the architecture of this phase. You should include the GUI with its view or views,
the corresponding presenters, a client and server com.example.shared.model with their classes, a poller, a server proxy, client
communicator, server communicator, and server facade. Please see the supplemented Design Rubric for
details.
Capabilities
A player must be able to
- register
- log in
- create a new game
- join a game that has not yet started
- provide a means for a game to start with two to five players

The list of existing games must automatically update as games are created, joined, started, etc.
Constraints
The design documentation must be done using both UML class diagrams and sequence diagrams.
Your design must show how you are to use each of the patterns mentioned in the patterns section. They
must be used in a meaningful way, each solving a real problem.
The design documentation must include your first weekly report for Phase 1 (See template. You can find
it near the bottom in the teams section)
A game may have as few as two and as many as five players.
Deliverables
Design documentation
A partially running Ticket to Ride game whose design follows the submitted design. It must provide all
of the capabilities described above.
Each member of the group is to turn in a copy of the group’s final weekly report for Phase 1 with their
individual Phase 1 report. (For the final weekly report see template. You can find it near the bottom in
the teams section)


****************************************************** PHASE 2 *******************************************************

Objectives
1. Fully implement (client and server side):
- game setup including the selection of 2 or 3 destination cards
- the Chat feature.
2. In the client side only: Implement all views for displaying game state except the game summary
(the last view). Demonstrate that views are updated for any modification to the client com.example.shared.model for
any part of the game except the game summary (the last view). (The server side of these views
will be implemented in the next phase).
3. Understand how to create and document a design.
Patterns
Update the old patterns for the new capabilities.
Requirements
Design
Create and document the architecture of this phase. You should provide UML diagrams for all classes
created or modified in Phase 2. Please see the Phase 2 Design Rubric for detail.
As part of the Phase 2 Design, you must also create two sequence diagrams: 1. Depict the whole flow for
selecting/discarding Destination Cards at the start of a game. 2. Depict the whole flow for adding to or
updating the chat history.
A player’s poller will periodically request commands from that server that it has not seen. This will
necessitate the development of command queues for each game. Conceptually there are also command
queues for each player. You must provide a means to ensure that when a client requests commands that it
gets only the ones it has not seen. This includes those that may have previously been lost in transmission.
As part of the Phase 2 Report, each team member should provide a single significant class documented
using Javadoc and Design-by-Contract style. The javadocs must include documentation for the public
fields (if any), public constructors, and public methods. Public constructors and method definitions must
include descriptions, parameter definitions (if any), exception definitions, and pre- and post- conditions.
The class description must provide invariants as needed.
Capabilities (These may be adjusted slightly depending on design)
Game setup capabilities:
- Assign a color to each player
-Determine player turn order
- Have the server randomly select 4 train cards for each player.
- For each player, have the server select 3 destination cards for the player, and allow
the player to discard 0 or 1 of them (initial destination card selection). This must be
fully implemented i.e. the server knows which (if any) card the player discarded and
it gets returned to the destination card deck.
Game view capabilities:
- Map/Game view:
- Show the map including all the cities and the routes between them.
- For claimed routes, show which player owns the route. Route ownership
must be indicated on the map itself (not elsewhere).
- PlayerInfo view:
- Have some sort of window, drawer, dialog box, or otherwise that shows
information about all players in the game. This includes their names, colors,
points, the order in which the players take turns, and indicate whose turn it
currently is.
- For this player, also show the specific train cards (i.e. their colors) and
destination cards owned (dealt by server at game initialization). For each
opponent player, show the number of train cards and destination cards
owned.
- Cards and card decks:
- For the train card deck, show the top 5 cards that are visible. Also show the
number of invisible cards in the deck.
- For the destination card deck, show the number of cards in the deck.
- Demonstrate that your views update dynamically when your client com.example.shared.model is modified.
This can be done through a variety of ways. One way would be to add a temporary
button to your main view. When pressed, this button could call a method on the
view’s presenter that makes a series of changes to the client com.example.shared.model. For each change,
the presenter could:
- Call the view to display a toast describing the change (e.g., “Updating player
points”).
- Make the indicated change to the local client com.example.shared.model (no server interaction is
involved here). This should cause your client com.example.shared.model to notify presenters of
the com.example.shared.model changes, and presenters should update their views appropriately.
- The following items should be demonstrated:
- Update player points
- Add/remove train cards for this player
- Add/remove player destination cards for this player
- Update the number of train cards for opponent players
- Update the number of train cars for opponent players
- Update the number of destination cards for opponent players
- Update the visible (face up) cards in the train card deck
- Update the number of invisible (face down) cards in train card deck
- Update the number of cards in destination card deck
- Add claimed route (for any player). Show this on the map.
- Add chat message from any player
- Advance player turn (change the turn indicator so it indicates another
player)
- Include pauses as needed to allow the TA to see each change.
- Each of the capabilities must be implemented by modifying the client com.example.shared.model
which in return uses the observable to notify the presenters (controllers),
which update the views.
Chat and Chat History:
- Fully implement the Chat feature.
- Players should be able to send chat messages at any time (even when it is not their
turn). Chat messages should appear in the same order on each player’s screen.
Constraints
The design documentation must be done using both UML class diagrams and sequence diagrams. It must
also have n classes that have been commented with Javadoc for each of the n members of your group
(part of the Phase 2 Report).
The design documentation must include your first weekly report for Phase 2 (See template. You can find
it near the bottom in the teams section)
A game may have as few as 2 and as many as 5 players.
Deliverables
Design documentation
A partially running Ticket to Ride game that can be setup and whose design follows the submitted design.
It must provide all of the capabilities described above.
You must provide a routine that can be run to test and demonstrate all of the client com.example.shared.model updates and
subsequent view updates (through the observable) for all capabilities described above. For every test case
the routine should describe what the test case is to do, and do it. It is to then wait a sufficient amount of
time (about 5 seconds) for the TA to read the description and observe the result in the GUI.
Each member of the group is to turn in a copy of the group’s final weekly report for Phase 1 with their
individual Phase 1 report. (For the final weekly report see template. You can find it near the bottom in
the teams section)


****************************************************** PHASE 3 *******************************************************

Objectives
1. Finish the implementation of all operations needed to play the game. This includes operations
triggered by changes in the user interface and operations sent from the server back to the client.
You must provide a means for the server to send the final results page to each player.
2. Understand how to create and document a design.
3. Learn how to apply the state pattern.
New Patterns
1. State Pattern – apply it to some non-trivial part of the program.
Requirements
Design
Provide a diagram or picture of how the final results page will look.
Create and document the architecture of this phase. You should provide UML diagrams for all
classes created or modified for Phase 3. These should include classes for both the client com.example.shared.model
and classes for the server com.example.shared.model. Also, include any new classes for command management on the
server.
Capabilities (These may be adjusted slightly depending on design)
In general you should provide for the following game playing activities
- Claim a route
- Ask for destination cards
- Send back 1 or 2 destination cards
- Select 1 or 2 train cards
- You must handle all the associated rules
The following are high-level view events generated by the GUI that you must handle (depending
on your design, these operations may cause the server to send commands back to the originating
client). The GUI must call methods on the “Presenters”. The presenters will then delegate
functionality to the state, which will know which actions are allowed and how to respond at any
given time.
- Claim a route
- Request destination cards
- Return destination card
- Choose face-up train card
- Choose train card from deck
- Chat
High-level operations the server will send to all players
- Last round
- Route claimed by player (you may include the number of train pieces needed to claim
the route)
- Current player requested destination cards
- Current player returned destination card
- Player chose train card of specific color from face-up train cards
- Player chose wild card
- New face up card chosen from deck
- Chat
- Game history update
- Display result page.
- Player’s turn ended
- Train card placed face up
- Update players points
- Game history
The result page need not be elaborate but must designate the winning player, and, for each player show:
- Number of points from claimed routes. The number of points for a claimed route must be
proportional to the length of the route as defined in the rules.
- Number of points for reached destinations.
- Number of negative points for unreached destinations.
- Number of points for having the most claimed routes (this is a simplification from a
requirement to calculate and give points for the longest route).
- The total number of points for the player.
- (Extra Credit) If the player has the longest path, show the number of points for that
accomplishment (this will substitute giving points for having the most claimed routes and will
be awarded extra credit).
Constraints
The design documentation must be done using both UML class diagrams and sequence diagrams.
The design documentation must include your first weekly report for Phase 3 (See template. You can find
it near the bottom in the teams section)
Deliverables
Design documentation
A totally running Ticket to Ride game whose design follows the submitted design. It must provide all of
the capabilities described above.
Each member of the group is to turn in a copy of the group’s final weekly report for Phase 1 with their
individual Phase 1 report. (For the final weekly report see template. You can find it near the bottom in
the teams section)


****************************************************** PHASE 4 *******************************************************

Overview
In this phase you will add data persistence to your Ticket-to-Ride server. This will allow your
server to be shutdown and restarted without losing its state (i.e., user accounts and games). You
will design a pluggable persistence subsystem that will allow your server to run with different
persistence implementations, and allow new persistence implementations to be plugged in
without recompiling your server. You will gain experience with the following design principles
and patterns:
1. Program to interfaces
2. Command Pattern
3. Data Access Object Pattern
4. Abstract Factory Pattern
5. Plugin Pattern
6. Database design, transactions, object serialization
New Patterns and Design Principles
1. Plugin Pattern
2. Abstract Factory Pattern
3. DAO Pattern
4. Database design, transactions, object serialization
Requirements
Modify your Ticket-to-Ride server to save its state to persistent storage. It should be possible to
shutdown your server and restart it without losing user account or game information. This
includes both intentional shutdowns and unintentional shutdowns caused by a crash, power
failure, etc. This will require you to keep the state of your in-memory server com.example.shared.model and
persistent storage in sync at all times. To achieve this, you should introduce a persistence
subsystem. After creating the persistence subsystem, you should modify your server’s web API
1
handlers to call the persistence subsystem to keep the in-memory com.example.shared.model in sync with persistent
storage.
Define the API for your persistence subsystem using abstract interfaces that can be implemented
using a variety of different underlying technologies (relational DB, document DB, cloud storage,
etc.) In designing your persistence API, you should use both the Data Access Object and
Abstract Factory design patterns as follows:
1. Design a “persistence provider” interface to serve as the entry point into your persistence
subsystem. This interface should provide operations for starting and ending transactions
and also for clearing existing data from the database. It should also serve as an Abstract
Factory for the user and game DAOs (described next).
2. Design a DAO interface for managing user accounts.
3. Design a DAO interface for managing games.
4. Design any additional DAOs (if any) as you see fit.
Implement the Plugin pattern so that different persistence provider implementations can be
plugged into your server without needing to recompile the server. This will require you to
provide a mechanism for registering external JAR files with your server. When the server is
started, the user should be able to specify the name of the persistence provider they want to use
during that execution of the server. The server should load, initialize, and use the specified
persistence provider until it shuts down. Once the persistence provider has been initialized, the
rest of the server should be unaware of what particular persistence technology is being used (i.e.,
all interaction with the persistence subsystem should be through the abstract provider and DAO
interfaces).
Plugins should be packaged as JAR files. Plugin implementations should be dynamically loaded
from their JAR files, and not compiled into the server. Your server must provide a way for
plugins to register themselves with the server. This is typically done by using a configuration
file that lists the name and JAR file path for each plugin. Another approach is to create a special
subdirectory where plugin JAR files are placed, and have the server scan this directory for
plugins.
Implement at least two different persistence provider plugins that use different underlying
technologies. One of the implementations should be a relational database. The other
implementation is your choice, but must be something other than a relational database.
2
You should store the state of each game as a BLOB (i.e., serialize the game’s state, and store the
serialized data). For a relational database, this will drastically reduce the number of tables you
will need -- you shouldn’t need more than about three or four tables in your schema.
In your persistence providers, rather than saving the entire state of a game each time it changes,
you should use a “checkpoint plus deltas” approach. Here is the basic idea:
1. When a game is created, store a BLOB containing its initial state. (This is the initial
checkpoint.)
2. Store the sequence of Commands executed in the game. Each time a Command is
executed, append it to the list.
3. The “current” state of a game can be computed by starting with the initial checkpoint and
replaying all of the Commands executed during the game. However, in order to speed up
this process, you should update the game’s state BLOB after every N commands (where
N is a command-line parameter to your server). This will allow the “current” game state
to be computed by starting with the most recent checkpoint, and replaying only those
Commands that have been executed since the checkpoint was last updated.
Your server should accept two command-line parameters:
1. The name of the persistence provider plugin to use during this execution of the server.
2. The number of Commands between game state checkpoints.
You should also provide a way to ‘clear’ the data in your persistence provider. You can do this
by adding another command-line parameter to the server (‘-wipe’ for example).
Design
Create UML class diagrams for the following:
1. Persistence provider and DAO interfaces and classes that will implement them.
2. Interfaces and classes related to your Plugin implementation.
3. Any other interfaces and classes central to your design.
Provide a write-up of how your two persistence provider implementations will work. Which
technologies will you be using? Explain how you will store all user account and game
information. Explain in detail how you will implement the “checkpoint plus deltas” approach for
storing game state. For your relational database provider, include the schema for your tables
(i.e., CREATE TABLE statements).
Provide a write-up explaining how you will implement the Plugin pattern to support pluggable
persistence providers. How will plugins be registered with your server? How will it load them?
3
How will the current persistence provider be accessed by classes that need to interact with the
persistence subsystem?
Turn your design into the TAs in the same way you turned in the design for phase 3.
Implementation
Implement your design.
In addition, the command line for our server is required to have two parameters:
“persistence_type” and “number_of_commands-between-checkpoints”
For example,
java ticketToRideServer sqlite 10
Constraints
The design documentation must be done using both UML class diagrams and sequence diagrams.
The design documentation must include your first weekly report for Phase 4 (See template. You can find
it near the bottom in the teams section)
Deliverables
Design
1. Design documentation
Implementation and Testing
1. Fully working server that supports pluggable persistence subsystems
2. At least two working persistence providers (one relational, one something else)
3. A way to ‘clear’ out the data in your persistence providers from the command line.
Each member of the group is to turn in a copy of the group’s final weekly report for Phase 1 with their
individual Phase 1 report. (For the final weekly report see template. You can find it near the bottom in
the teams section)

