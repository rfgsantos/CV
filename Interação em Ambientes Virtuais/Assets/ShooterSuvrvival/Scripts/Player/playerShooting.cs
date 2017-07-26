using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class playerShooting : MonoBehaviour {
	public float damage = 10f;
	public float range = 100f;
	public float timeBetweenBullets = 0.15f;
	private float timer;
	private AudioSource[] audio;

	Ray shootRay = new Ray();
	ParticleSystem ps;
	GameObject camera;
	int shootableMask;

	// Use this for initialization
	void Start () {
		//check all childrens
		ps = GetComponentInChildren<ParticleSystem> ();
		camera = transform.Find ("fps-camera").gameObject;
		shootableMask = LayerMask.GetMask ("Shootable");
		audio = GetComponents<AudioSource> ();
	}
	
	// Update is called once per frame
	void FixedUpdate () {
		timer += Time.deltaTime;

		if(Input.GetButton("Fire1") && timer >= timeBetweenBullets && Time.timeScale != 0){
			shooting ();
		}
	}

    void shooting()
    {
		timer = 0f;
		audio[0].Play ();
		ps.Stop ();
		ps.Play ();
		shootRay.origin = camera.transform.position;
		shootRay.direction = camera.transform.forward;
		RaycastHit rayhit;
		if(Physics.Raycast(shootRay, out rayhit, range, shootableMask)){
			enemyHealth target = rayhit.transform.GetComponent <enemyHealth>();
			if(target != null){
				target.takeDamage (damage,rayhit.point);
			}
		}



    }


}
