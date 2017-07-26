using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class spawnManager : MonoBehaviour {
	private GameObject player;
	private Vector3 playerPos;
	public GameObject[] zombies;
	private int maxRange, minRange;
	public float spawnTime;
	private playerHealth ph;
	public GameObject spawner;
	// Use this for initialization
	void Start () {
		player = GameObject.FindGameObjectWithTag ("Player");
		playerPos = player.transform.position;
		ph = player.GetComponent<playerHealth> ();
		maxRange = 40;
		minRange = 10;
		InvokeRepeating ("spawnZombies",spawnTime,spawnTime);
	}

	private void spawnZombies(){
		if(ph.health <= 0){
			return;
		}
		//actualizar a posição do jogador
		playerPos = player.transform.position;
		foreach(GameObject zombie in zombies){
			Vector3 newPos = new Vector3(playerPos.x,playerPos.y,playerPos.z);
			newPos.x += Random.Range(minRange,maxRange);
			newPos.z += Random.Range(minRange,maxRange);
			spawner.transform.position = newPos;
			//objecto, x,y,z
			Instantiate (zombie,spawner.transform.position,spawner.transform.rotation);
		}

		//cancelar o invoke especifico
		//CancelInvoke ("spawnZombies");
	}
}
