 Greg Daniels
Assignment 4

 Honestly I didnt do much with the active behaviors of the agents because I spent a considerable amount of time trying to determin why the java libraries were crashing the game server every time i tried to connect to it.

All of the GetResourceExecution strategies are completed and tested(where getResource is a resource request to any agent or the gameboard ie agentlist) as well as join game and time tick.

Testing Active behaviour seems untestable at the moment because of the state of the gameserver ie it crashes everytime you send something that isnt a join game request.

All of the unit tests from assignment three were broken by the new libraries and i didnt have time to reimplement them.

 For the Advanced requirements I implemented a gui for the agents. When you run the project agentgui it provides an interface to select the agenttype as well as which game to join. it then goes to a different screen that is constantly updated with the status of the agent.

Also the projects were refactord such that there are projects for each type of agent a project for agentCommon a project for the communicator and a prject for the common libraries.