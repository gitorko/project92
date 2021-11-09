package com.demo.project92.config;

import java.security.Principal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StompPrincipal implements Principal {
    String name;
}
