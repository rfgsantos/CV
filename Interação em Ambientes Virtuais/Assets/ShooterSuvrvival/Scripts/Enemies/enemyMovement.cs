
using UnityEngine;

public class enemyMovement : MonoBehaviour {
	Transform player;
	playerHealth playerHealth;
	UnityEngine.AI.NavMeshAgent nav;
	// Use this for initialization
	Animator anim;
	AnimatorStateInfo animInfo;
	private AudioSource[] audio;

	public float speed;

	void Start () {
		player = GameObject.FindGameObjectWithTag("Player").transform;
		playerHealth = player.GetComponent<playerHealth> ();
		nav = GetComponent<UnityEngine.AI.NavMeshAgent> ();
		speed = nav.speed;
		//aumentar speed da animação
		anim = GetComponentInChildren<Animator>();
		audio = GetComponents<AudioSource> ();

	}
	
	// Update is called once per frame
	void Update () {
		animInfo = anim.GetCurrentAnimatorStateInfo (0);
		bool info = animInfo.IsTag("Spawn");

		if(!info){
			if (playerHealth.health > 0) {
				Vector3 playerpos = player.position;
				nav.SetDestination (playerpos);
			} else {
				nav.enabled = false;
			}
		}

		if(animInfo.IsName("Reincarnating")){
			if(!audio[0].isPlaying){
				audio [0].Play ();
			}
		}
		if(Vector3.Distance(player.position, transform.position) <= 20){
			if(animInfo.IsName("Walking")){
				if(!audio[1].isPlaying){
					audio [1].Play ();
				}
			}
		}

	}

}
