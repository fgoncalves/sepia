package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.domain.models.Puppy

fun getAllPuppies() = FakeDb.all().map(PuppyEntity::toDomainPuppy)

fun getPuppy(id: String) = FakeDb.get(id)?.toDomainPuppy()

private fun PuppyEntity.toDomainPuppy() = Puppy(
    id = id,
    name = name,
    avatar = avatar,
    association = association,
    weightKg = weightKg,
    ageMonths = ageMonths,
    breed = breed,
    gender = gender,
    specialNeeds = specialNeeds,
    neutered = neutered,
    adoptionState = adoptionState,
    bio = bio,
)