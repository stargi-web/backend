package com.stargi.backend.iam.domain.commands;

public record EditPasswordCommand(Long userId,String newPassword) {
}
