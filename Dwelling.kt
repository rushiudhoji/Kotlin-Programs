/**
* Program that implements classes for different kinds of dwellings.
* Shows how to:
* Create class hierarchy, variables and functions with inheritance,
* abstract class, overriding, and private vs. public variables.
*/
import kotlin.math.PI
import kotlin.math.sqrt
fun main(){
  val squareCabin = SquareCabin(6, 50.0)

  with(squareCabin){
    println("\nSquare Cabin\n==========")
    println("Capacity:${capacity}")
    println("Material:${buildingMaterial}")
    println("Has Room?${hasRoom()}")
    println("Floor Area: ${floorArea()}")
  }
  val roundHut = RoundHut(3,10.0)
  with(roundHut){
    println("\nSquare Cabin\n==========")
    println("Capacity:${capacity}")
    println("Material:${buildingMaterial}")
    println("Floor Area: ${floorArea()}")
    println("Has room? ${hasRoom()}")
    getRoom()
    println("Has room? ${hasRoom()}")
    getRoom()
    println("Carpet Size: ${calculateMaxCarpetSize()}")
  }
  val roundTower = RoundTower(4,15.5)
  with(roundTower){
    println("\nSquare Cabin\n==========")
    println("Capacity:${capacity}")
    println("Material:${buildingMaterial}")
    println("Floor Area:${floorArea()}")
    println("Carpet Size: ${calculateMaxCarpetSize()}")
  }
}

/**
* Defines properties common to all dwellings.
* All dwellings have floorspace,
* but its calculation is specific to the subclass.
* Checking and getting a room are implemented here
* because they are the same for all Dwelling subclasses.
*
* @param residents Current number of residents
*/
abstract class Dwelling(private var residents: Int){
  abstract val buildingMaterial: String
  abstract val capacity:Int
  /**
      * Calculates the floor area of the dwelling.
      * Implemented by subclasses where shape is determined.
      *
      * @return floor area
      */
  abstract fun floorArea(): Double
  /**
      * Checks whether there is room for another resident.
      *
      * @return true if room available, false otherwise
      */
  fun hasRoom():Boolean{
    return residents < capacity
  }

  /**
    * Compares the capacity to the number of residents and
    * if capacity is larger than number of residents,
    * add resident by increasing the number of residents.
    * Print the result.
    */
  fun getRoom(){
    if(capacity > residents){
      residents++
      println("You got a Room!")
    }
    else{
      println("Sorry,at capacity and no rooms left.")
    }
  }
}

/**
* A square cabin dwelling.
*
*  @param residents Current number of residents
*  @param length Length
*/
class SquareCabin(residents: Int, val length: Double): Dwelling(residents){
  override val buildingMaterial = "Wood"
  override val capacity = 6

  override fun floorArea(): Double{
    return length * length
  }
}
open class RoundHut(residents: Int, val radius: Double):Dwelling(residents){
  override val buildingMaterial = "Straw"
  override val capacity = 4

  /**
    * Calculates floor area for a square dwelling.
    *
    * @return floor area
    */
  override fun floorArea(): Double{
    return PI * radius * radius
  }
  /**
    *  Calculates the max length for a square carpet
    *  that fits the circular floor.
    *
    * @return length of carpet
    */
  fun calculateMaxCarpetSize(): Double{
    val diameter = 2 * radius
    return sqrt(diameter * diameter / 2)
  }
}
/**
* Round tower with multiple stories.
*
* @param residents Current number of residents
* @param radius Radius
* @param floors Number of stories
*/
class RoundTower(residents: Int, radius: Double, val floors: Int = 2): RoundHut(residents,radius){
  override val buildingMaterial = "Stone"
  // Capacity depends on the number of floors.
  override val capacity = 4 * floors
  /**
    * Calculates the total floor area for a tower dwelling
    * with multiple stories.
    *
    * @return floor area
    */
    override fun floorArea(): Double {
      return super.floorArea() * floors
    }
}
