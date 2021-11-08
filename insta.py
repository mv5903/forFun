import instaloader

# Get instance
L = instaloader.Instaloader()

username = 'kwikmatt'

# Login 
L.interactive_login(username)
profile = instaloader.Profile.from_username(L.context, username)
followers = []
i = 1

# Extract list of followers, then see which 'following' is not in followers list

for follower in profile.get_followers():
    followers.append(str(follower))

for followee in profile.get_followees():
    if str(followee) not in followers:
        print('NOT FOLLOWING BACK: ' + str(i) + ' ' + str(followee))
        i+=1

