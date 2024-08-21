package com.org.placeholder.dto;

import lombok.Data;

/**
 * Represents a post in the system.
 *
 * The {@code PostBody} class holds information about a post, including
 * the post ID, user ID, title, and body content. This class uses
 * Lombok's {@link Data} annotation to automatically generate
 * boilerplate code such as getters, setters, equals, hashCode, and toString methods.
 */
@Data
public class PostBody
{
    private int id;
    private int userId;
    private String title;
    private String body;
}
