# [Programmers Palace](https://discord.gg/48Zxcpy) - Game Jam 2020
## Introduction
Hey, so first. I'm Doomer - a participant of the Programmers Palace Game Jam 2020. I usually like to go crazy, so
this time, I thought about, what if, I create the whole project in plain Java, without __any__ frameworks and in a non-graphical
environment. The idea is, to code this in a tty with vim, build it with gradle and after I've done a substantial part of the
code I would test the build in a graphical environment. I will try to document thoughts / ideas / bugs here, so the next few
sections are going to be dedicated to a daily report kind of thing. I also decided, in case that everything fails to revert to
Godot. Just in case this repository will disappear into the Nirvana... You know what happened.

## First Thoughts / Ideas
So, I have various plans to approach this Game Jam and for me all sound super neat. The first one would be an even stricter
reinforcement of the previously mentioned rule: Code a Game, which **does not require** a graphical interface and yet has
graphical aspects. When I say this, I imagine doing stuff like [this](https://ttygames.files.wordpress.com/2013/04/ascii-invaders.png), 
[that](https://i.imgur.com/366q2.png) or [that](https://3.bp.blogspot.com/-wMzTp8R82Sw/Tn1qHjcJQBI/AAAAAAAAADs/dLtoJKoHut8/s1600/arrp_demo_orchard.png).
Maybe not as cool as those, since those look very complicated, and you probably have to have a degree in ascii-dynamics to
make everything looks as fancy as they made it look.  
Another option I consider would be an isometric tiles game, such as [this one](http://fantasy-maps.com/wp-content/uploads/2013/08/iph-06-thefuture.png).
That would be awesome and is probably approximately as complicated as the first idea, since I'd have to render everything
exactly where it should be - height might become a bit different to implement, but we'll see. That would definitely be a choice
I would not regret - always wanted to make such a thing, but with OpenGL it turned out to be a bit complicated, due to my
lack of GLSL knowledge, plus I am still not quite sure about most functions, what exactly they do, so I guess for that I'm
safer with Java. I initially planned on using my Source Engine for that purpose, but I think I'll need to rewrite quite a few
parts, to get where I want to be.

## Day 1
Today's day one of the Game Jam, I've created a mindmap of thing I want to achieve in this project, and I'm really looking forward
to make as much out of it as possible! I think I'll start off with a basic rendering engine for an isometric perspective, a basic
game world, which contains tiles and tiles which map to certain textures and properties. I plan on doing that using some simple
vector / matrix maths. So I guess I'll start it up by creating logger / maths / render and world packages to organize the project
structure a little :). Of course an App Class as well to rule them all!
