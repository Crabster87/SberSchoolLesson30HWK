package crabster.rudakov.sberschoollesson30hwk.domain.interactors

import crabster.rudakov.sberschoollesson30hwk.domain.repositories.ILocalImageRepository

class LocalImageInteractorImpl(
    private val localImageRepository: ILocalImageRepository
) : ILocalImageInteractor {

    override fun getLocalImages() =
        localImageRepository.getLocalImages()

}