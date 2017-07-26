using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CamMouseLookup : MonoBehaviour {

    //track of how much movement the camera has made
    Vector2 mouseLook;
    //change since the last movement
    Vector2 smoothV;

    //public variables to keep control
    public float sensitivity = 5.0f;
    public float smoothing = 2.0f;

    //the character which the camera will act has the "eye sight"
    GameObject character;  
	
    // Use this for initialization
	void Start () {
        //sendo que o pai é o objecto FPS, caso não fosse, uma variavel public transform se usava.
        //no nosso contexto, PARENT = ARMYPILOT
        character = this.transform.parent.gameObject;
	}
	
	// Update is called once per frame
	void FixedUpdate () {

        //mouse delta, values of the mouse actual diferential
        var md = new Vector2(Input.GetAxisRaw("Mouse X"), Input.GetAxisRaw("Mouse Y"));

        md = Vector2.Scale(md, new Vector2(sensitivity * smoothing, sensitivity * smoothing));

        smoothV.x = Mathf.Lerp(smoothV.x,md.x,1f/smoothing);
        smoothV.y = Mathf.Lerp(smoothV.y,md.y, 1f/smoothing);
        mouseLook += smoothV;
        mouseLook.y = Mathf.Clamp(mouseLook.y,-50f,90f);

        transform.localRotation = Quaternion.AngleAxis(-mouseLook.y, Vector3.right);
        character.transform.localRotation = Quaternion.AngleAxis(mouseLook.x, character.transform.up);
	}
}
