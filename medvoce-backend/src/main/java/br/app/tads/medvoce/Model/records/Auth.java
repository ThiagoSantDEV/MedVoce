package br.app.tads.medvoce.Model.records;

import br.app.tads.medvoce.Model.enums.Groups;

public record Auth(String token, String name, Groups group, Long id) {
}
