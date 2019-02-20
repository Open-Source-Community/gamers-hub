package com.an.gamers.AdminstrationLayouts;

import android.support.annotation.NonNull;

import com.an.gamers.Model_Classes.Game;
import com.an.gamers.Model_Classes.Group;
import com.an.gamers.Model_Classes.Platform;
import com.an.gamers.Model_Classes.Post;
import com.an.gamers.Model_Classes.User;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adminstration {
    private User currentuser;
    private Map<String,Group> mGroups;
    private Map<String,Platform> mPlateforms;
    private Map<String,Post> mPosts;
    private Map<String,User> musers;
    private Map<String,Game> mGames;
    private DatabaseReference databaseRef;
    private String users_s;
    private String groups_s;
    private String games_s;
    private String posts_s;
    private String platform_s;

    public Adminstration() {
        mGroups=new HashMap<>();
        mPlateforms=new HashMap<>();
        mPosts=new HashMap<>();
        musers=new HashMap<>();
        mGames=new HashMap<>();
        users_s = "users";
        groups_s="groups";
        posts_s = "posts";
        platform_s= "platforms";
        games_s = "games";
    }

    public Adminstration(User currentuser) {
        this.currentuser = currentuser;

    }
    public void Adduser(User newuser) {
        databaseRef= FirebaseDatabase.getInstance().getReference(users_s);
        databaseRef.child(newuser.getmId_user()).setValue(newuser);
    }
    public void AddGame(Game newGame) {
        databaseRef= FirebaseDatabase.getInstance().getReference(games_s);
        databaseRef.child(newGame.getGroupId()).setValue(newGame);
    }
    public void AddPlateform(Platform newPlatform) {
        databaseRef= FirebaseDatabase.getInstance().getReference(platform_s);
        databaseRef.child(newPlatform.getmGroupId()).setValue(newPlatform);
    }
    public void AddPost(Post newPost) {
        databaseRef= FirebaseDatabase.getInstance().getReference(posts_s);
        databaseRef.child(newPost.getmId()).setValue(newPost);
    }
    public void AddGroup(Group newGroup) {
        databaseRef= FirebaseDatabase.getInstance().getReference(groups_s);
        databaseRef.child(newGroup.getmID()).setValue(newGroup);
    }
    //Onstart bta3et login
    public void RetriveUsers() {
        databaseRef= FirebaseDatabase.getInstance().getReference(users_s);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot usersnap:dataSnapshot.getChildren())
                {
                    User user=usersnap.getValue(User.class);
                    musers.put(user.getmId_user(),user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //Onstart bta3et el games
    public void RetriveGames() {
        databaseRef= FirebaseDatabase.getInstance().getReference(games_s);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot gamesnap:dataSnapshot.getChildren())
                {
                    Game game=gamesnap.getValue(Game.class);
                    mGames.put(game.getmID(),game);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //Onstart bt3et el plateforms
    public void RetrivePlateforms() {
        databaseRef= FirebaseDatabase.getInstance().getReference(platform_s);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot platformsnap:dataSnapshot.getChildren())
                {
                    Platform platform =platformsnap.getValue(Platform.class);
                    mPlateforms.put(platform.getmID(), platform);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //Onstart bta3et el com.an.gamers.Model_Classes.Group Or Newsfeed(Home)
    public void RetrivePosts(){
        databaseRef= FirebaseDatabase.getInstance().getReference(posts_s);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnap:dataSnapshot.getChildren())
                {
                    Post post=postsnap.getValue(Post.class);
                    mPosts.put(post.getmId(),post);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //Onstart Plateforms and Games
    public void RetriveGroups() {
        databaseRef= FirebaseDatabase.getInstance().getReference(groups_s);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot groupsnap:dataSnapshot.getChildren())
                {
                    Group group=groupsnap.getValue(Group.class);
                    mGroups.put(group.getmID(),group);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void Edituser(User updateuser) {
        databaseRef= FirebaseDatabase.getInstance().getReference(users_s).child(updateuser.getmId_user());
        databaseRef.setValue(updateuser);
    }
    public void Editgame(Game updategame){
        databaseRef= FirebaseDatabase.getInstance().getReference(games_s).child(updategame.getmID());
        databaseRef.setValue(updategame);
    }
    public void EditPlateform(Platform updateplatform) {
        databaseRef= FirebaseDatabase.getInstance().getReference(platform_s).child(updateplatform.getmID());
        databaseRef.setValue(updateplatform);
    }
    public void Editgroup(Group updategroup)    {
        databaseRef= FirebaseDatabase.getInstance().getReference(groups_s).child(updategroup.getmID());
        databaseRef.setValue(updategroup);
    }
    public void Editpost(Post updatepost) {
        databaseRef= FirebaseDatabase.getInstance().getReference(posts_s).child(updatepost.getmId());
        databaseRef.setValue(updatepost);
    }
    public void deleteuser(String userid) {
        databaseRef= FirebaseDatabase.getInstance().getReference(users_s).child(userid);
        databaseRef.removeValue();
    }
    public void deleteplatform(String platformid) {
        databaseRef= FirebaseDatabase.getInstance().getReference(platform_s).child(platformid);
        databaseRef.removeValue();
    }
    public void deletegroup(String groupid) {
        databaseRef= FirebaseDatabase.getInstance().getReference(groups_s).child(groupid);
        databaseRef.removeValue();
    }
    public void deletepost(String postid) {
        databaseRef= FirebaseDatabase.getInstance().getReference(posts_s).child(postid);
        databaseRef.removeValue();
    }
    public void deletegame(String gameid) {
        databaseRef= FirebaseDatabase.getInstance().getReference(games_s).child(gameid);
        databaseRef.removeValue();
    }
    public boolean login(String username,String password) {
        List<User> userlist = new ArrayList<User>(musers.values());
        for(User user:userlist)
        {
            if((user.getmUserName().equals(username) || user.getmEmail().equals(username))&&user.getmPassword().equals(password))
            {
                currentuser=user;
                return true;
            }

        }
        return false;
    }

    public void user_addgame(final Game addedgame, String user_id){
        databaseRef = FirebaseDatabase.getInstance().getReference(users_s).child(user_id);
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User temp = dataSnapshot.getValue(User.class);
                List<String> favgames = temp.getmFav_Games();
                favgames.add(addedgame.getmID());
                temp.setmFav_Games(favgames);
                databaseRef.setValue(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    };
    public void user_addpost(Post addedpost, String user_id, String groupid){
        //addes post to a user and a group
        //add post to list of posts in user using its id
        databaseRef = FirebaseDatabase.getInstance().getReference(posts_s);
        DatabaseReference groupref = FirebaseDatabase.getInstance().getReference(groups_s);
        DatabaseReference userref = FirebaseDatabase.getInstance().getReference(users_s);

        final String key = databaseRef.push().setValue(addedpost).getkey();


        //databaseroot.child(groups_s).child(groupid).child(posts_s).push(post_id);
        databaseRef = FirebaseDatabase.getInstance().getReference(users_s).child(user_id);
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //add post to list of posts in the group using its id
                User temp = dataSnapshot.getValue(User.class);
                List<String> posts = temp.getlPostsid();
                posts.add(key);
                temp.setlPostsid(posts);
                databaseRef.setValue(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    };
    public void user_addplatform(Platform addedplat, String user_id){
        //add platform to user's fav platform list
        //databaseroot.child("user").child(user_id).child("favplats").push(addedplat.getid());

    };
    public void create_group(Game groupgame){
        Group added = new Group();
        //databaseroot.child(groups_s).push(added);

        //added.setid(groupgame.getname());
        //create group id
        //added.setname(groupgame.getname+" community");

    }
    public Post getbyid_post(String search_id){
        Post post=new Post();
        if(mPosts.containsKey(search_id))
            post =(Post) mPosts.get(search_id);
        return post;
    }
    public Game getbyid_game(String search_id){
        Game game=new Game();
        if(mGames.containsKey(search_id))
            game =(Game) mGames.get(search_id);
        return game;
    }
    public Platform getbyid_platform(String search_id){
        Platform plat=new Platform();
        if(mPlateforms.containsKey(search_id))
            plat =(Platform) mPlateforms.get(search_id);
        return plat;
    }

}
