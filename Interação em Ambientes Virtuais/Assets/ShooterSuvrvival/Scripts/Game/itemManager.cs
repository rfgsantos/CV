using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class itemManager : MonoBehaviour {

	// - 0 is life
	// - 1 is bomb
	public GameObject[] items;
	public GameObject itemSpawner;
	private GameObject player;
	private int maxRange, minRange;
	private int spawnTimeBomb, spawnTimeLife,spawnTimeBoost;
	private Vector3 playerPos;

	// Use this for initialization
	void Start () {
		// 2 min para bomba
		spawnTimeBomb = 240;
		// 30 segundos para vida
		spawnTimeLife = 30;
		// 1min e 30 segundos para damage boost
		spawnTimeBoost = 90;
		//limites
		maxRange = 20;
		minRange = 10;
		player = GameObject.FindGameObjectWithTag ("Player");
		playerPos = player.transform.position;
		InvokeRepeating ("spawnBomb",spawnTimeBomb,spawnTimeBomb);
		InvokeRepeating ("spawnLife",spawnTimeLife,spawnTimeLife);
		InvokeRepeating ("spawnBoost",spawnTimeBoost,spawnTimeBoost);
	}

	private void spawnBomb(){
		playerPos = player.transform.position;
		Vector3 newPos = new Vector3(playerPos.x,playerPos.y,playerPos.z);
		newPos.x += Random.Range(minRange,maxRange);
		newPos.z += Random.Range(minRange,maxRange);
		itemSpawner.transform.position = newPos;
		Instantiate (items[1],itemSpawner.transform.position,itemSpawner.transform.rotation);
	}

	private void spawnLife(){
		playerPos = player.transform.position;
		Vector3 newPos = new Vector3(playerPos.x,playerPos.y,playerPos.z);
		newPos.x += Random.Range(minRange,maxRange);
		newPos.z += Random.Range(minRange,maxRange);
		itemSpawner.transform.position = newPos;
		Instantiate (items[0],itemSpawner.transform.position,itemSpawner.transform.rotation);
	}

	private void spawnBoost(){
		playerPos = player.transform.position;
		Vector3 newPos = new Vector3(playerPos.x,playerPos.y,playerPos.z);
		newPos.x += Random.Range(minRange,maxRange);
		newPos.z += Random.Range(minRange,maxRange);
		itemSpawner.transform.position = newPos;
		Instantiate (items[2],itemSpawner.transform.position,itemSpawner.transform.rotation);
	}
}
