package com.vrise.bazaar.newpages.top

/*import androidx.room.**/

/*
@Dao
interface TopDao {

    @Query("SELECT * FROM topmodel")
    fun getAll(): LiveData<List<TopModel>>

    @Query("SELECT * FROM topmodel WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): LiveData<List<TopModel>>

    @Query("SELECT * FROM topmodel WHERE location LIKE :first LIMIT 1")
    fun findByName(first: String): LiveData<TopModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: TopModel)

    */
/*@Query("UPDATE topmodel SET location = :location AND latlng = :latlng")
    fun updateLocation(location: String, latlng: String)*//*


    */
/*@Query("UPDATE topmodel SET notification_count = :notifications, cart_count = :cart AND favourites_count = :fav")
    fun updateCounts(notifications: Int, cart: Int, fav: Int)*//*


    @Query("UPDATE topmodel SET cart_count = :cart")
    fun updateCart(cart: Int)

    @Query("UPDATE topmodel SET notification_count = :notify")
    fun updateNotificationCounts(notify: Int)

    @Query("UPDATE topmodel SET favourites_count = :fav")
    fun updateFavourite(fav: Int)

    */
/*@Query("SELECT * FROM topmodel LIMIT 1")
    fun getItem(first: String): LiveData<TopModel>*//*


    @Delete
    fun delete(user: TopModel)

    @Query("DELETE FROM topmodel")
    fun nukeTable()
}*/
