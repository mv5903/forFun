import instaloader

# Get instance
L = instaloader.Instaloader()

username = 'kwikmatt'

# Login 
L.interactive_login(username)
profile = instaloader.Profile.from_username(L.context, username)
followers = []
followees = []
i = 1

# Extract list of followers, then see which 'following' is not in followers list

counter = 1
for follower in profile.get_followers():
    print('Scraping Followers: ' + str(counter), end="\r")
    counter+=1
    followers.append(str(follower))

print('Follower Scrape Complete')

counter = 1
for followee in profile.get_followees():
    print('Scraping Followings: ' + str(counter), end="\r")
    counter+=1
    followees.append(str(followee))

print('Displaying those who don\'t follow you back: ')
    
for followee in followees:
    if str(followee) not in followers:
        print('NOT FOLLOWING BACK: ' + str(i) + ' ' + str(followee))
        i+=1

