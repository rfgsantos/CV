using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class pickupDestroy : MonoBehaviour {
	private GameObject[] zombies;
	private enemyHealth zombieHealth;
	// Use this for initialization

	private void OnTriggerEnter(Collider other)
	{

		//do something to player here.
		//assim zombies não apanham os boosts
		if(other.name == "Player"){
			Destroy(this.gameObject);
			destroyZombies ();
		}

	}

	private void destroyZombies(){
		zombies = GameObject.FindGameObjectsWithTag ("Zombie");
		foreach(GameObject zombie in zombies){
			zombieHealth = zombie.GetComponent<enemyHealth> ();
			zombieHealth.Die ();
		}
	}
}
