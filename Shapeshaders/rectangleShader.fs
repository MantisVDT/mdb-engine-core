#version 330 core

in vec2 TexCoords;

uniform sampler2D Texture;

void main()
{
    FragColor = texture(Texture, TexCoords);
}