#version 330 core

layout (location = 0) in vec2 position;
layout (location = 1) in vec2 texCoords;

uniform mat4 projection;
uniform mat4 modelViewMatrix;

out vec2 textureCoords;

void main(void) {
    textureCoords = texCoords;
    gl_Position = projection * modelViewMatrix * vec4(position, 0.0, 1.0);
}