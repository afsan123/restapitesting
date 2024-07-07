package utility;

import utility.DTO.Post;

import java.util.Comparator;
import java.util.List;

public class ListUtil {
    public static List<Post> sortById(List<Post> posts) {
        posts.sort(Comparator.comparingInt(Post::getId));
        return posts;
    }
}
