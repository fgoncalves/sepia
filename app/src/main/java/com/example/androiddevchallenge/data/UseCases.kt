package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.domain.models.Puppy

fun getAllPuppies() = FakeDb.all().map(PuppyEntity::toDomainPuppy)

fun getPuppy(id: String) = FakeDb.get(id)?.toDomainPuppy()

fun updatePuppy(puppy: Puppy) = FakeDb.update(puppy.toEntityPuppy())

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

private fun Puppy.toEntityPuppy() = PuppyEntity(
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