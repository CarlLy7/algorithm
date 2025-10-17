package hot100;

import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.17
 * @Since: 1.0
 */

public class day20251017Solution {
    // [355] 设计推特
    class Twitter {
        // 记录发帖时间
        int gloablTime = 0;
        Map<Integer, User> idToUser;

        // 文章类
        class Tweet {
            private int id;
            private int timeTime;
            private Tweet next;

            public Tweet(int id) {
                this.id = id;
                this.timeTime = gloablTime++;
                this.next = null;
            }

            public int getId() {
                return id;
            }

            public int getTimeTime() {
                return timeTime;
            }

            public Tweet getNext() {
                return next;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setTimeTime(int timeTime) {
                this.timeTime = timeTime;
            }

            public void setNext(Tweet next) {
                this.next = next;
            }

            public boolean equals(Tweet other) {
                return id == other.id;
            }
        }

        // 用户类
        class User {
            private int id;
            // 最新的一篇文章
            private Tweet tweetHead;
            // 我的关注
            private HashSet<User> followedUserSet;

            public User(int id) {
                this.id = id;
                this.tweetHead = null;
                followedUserSet = new HashSet<>();
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Tweet getTweetHead() {
                return tweetHead;
            }

            public void setTweetHead(Tweet tweetHead) {
                this.tweetHead = tweetHead;
            }

            public HashSet<User> getFollowedUserSet() {
                return followedUserSet;
            }

            public void setFollowedUserSet(HashSet<User> followedUserSet) {
                this.followedUserSet = followedUserSet;
            }

            public boolean equals(User other) {
                return id == other.id;
            }

            /**
             * 关注用户
             * 
             * @param user
             */
            public void followUser(User user) {
                followedUserSet.add(user);
            }

            /**
             * 取关
             * 
             * @param user
             */
            public void unFollow(User user) {
                followedUserSet.remove(user);
            }

            /**
             * 发布文章
             * 
             * @param tweet
             */
            public void post(Tweet tweet) {
                tweet.next = this.tweetHead;
                tweetHead = tweet;
            }

        }

        public Twitter() {
            gloablTime = 0;
            idToUser = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            // 如果用户不存在
            if (!idToUser.containsKey(userId)) {
                User newUser = new User(userId);
                idToUser.put(userId, newUser);
            }
            User user = idToUser.get(userId);
            user.post(new Tweet(tweetId));
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            if (!idToUser.containsKey(userId)) {
                return res;
            }
            PriorityQueue<Tweet> priorityQueue = new PriorityQueue<>((a, b) -> {
                return b.getTimeTime() - a.getTimeTime();
            });
            int count = 0;
            User user = idToUser.get(userId);
            if (user.getTweetHead() != null) {
                priorityQueue.offer(user.getTweetHead());
            }
            if (user.getFollowedUserSet().size() > 0) {
                for (User otherUser : user.getFollowedUserSet()) {
                    if (otherUser.getTweetHead() != null) {
                        priorityQueue.offer(otherUser.getTweetHead());
                    }
                }
            }
            while (count < 10 && !priorityQueue.isEmpty()) {
                Tweet curTweet = priorityQueue.poll();
                res.add(curTweet.getId());
                count++;
                if (curTweet.getNext() != null) {
                    priorityQueue.offer(curTweet.getNext());
                }
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {
            if (!idToUser.containsKey(followerId)) {
                idToUser.put(followerId, new User(followeeId));
            }
            if (!idToUser.containsKey(followeeId)) {
                idToUser.put(followeeId, new User(followeeId));
            }
            User user = idToUser.get(followerId);
            user.followUser(idToUser.get(followeeId));
        }

        public void unfollow(int followerId, int followeeId) {
            // 取关的时候，如果有用户不存在，直接返回
            if (!idToUser.containsKey(followerId) || !idToUser.containsKey(followeeId)) {
                return;
            }
            User user = idToUser.get(followerId);
            user.unFollow(idToUser.get(followeeId));
        }
    }
}
