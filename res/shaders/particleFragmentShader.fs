#version 330 core

in vec2 textureCoords;

out vec4 out_color;

uniform sampler2D texture2D;

void main(void) {
    out_color = texture(texture2D, textureCoords);
}